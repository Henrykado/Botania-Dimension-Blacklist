package henrykado.botaniadimblacklist.asm;

import henrykado.botaniadimblacklist.BDB_Config;
import net.minecraft.launchwrapper.IClassTransformer;

import net.minecraft.world.World;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ASMTransformer implements IClassTransformer, Opcodes {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("vazkii.botania.common.core.handler.BiomeDecorationHandler".equals(transformedName))
        {
            ClassNode classNode = new ClassNode();
            new ClassReader(basicClass).accept(classNode, ClassReader.SKIP_FRAMES);

            for(MethodNode method : classNode.methods)
            {
                if ("onWorldDecoration".equals(method.name)) {
                    LabelNode label = new LabelNode();

                    InsnList insnList = new InsnList();
                    insnList.add(new VarInsnNode(ALOAD, 0));
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraftforge/event/terraingen/DecorateBiomeEvent$Decorate", "getWorld", "()Lnet/minecraft/world/World;"));
                    insnList.add(new MethodInsnNode(INVOKESTATIC, "henrykado/botaniadimblacklist/asm/ASMTransformer", "flowerBlacklist", "(Lnet/minecraft/world/World;)Z"));
                    insnList.add(new JumpInsnNode(IFEQ, label));

                    method.instructions.insert(insnList);
                    method.instructions.add(label);
                    method.instructions.add(new InsnNode(RETURN));
                    break;
                }
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }

        return basicClass;
    }

    public static boolean flowerBlacklist(World world)
    {
        String[] blacklist = BDB_Config.flowerDimensionBlacklist;
        if (blacklist.length > 0)
        {
            boolean isWhitelist = blacklist[0].equals("*");
            for (int i = (isWhitelist ? 1 : 0); i < blacklist.length; i++)
            {
                if (!isWhitelist)
                {
                    if (world.provider.getDimension() == Integer.parseInt(blacklist[i]))
                        return false;
                }
                else
                {
                    if (world.provider.getDimension() != Integer.parseInt(blacklist[i]))
                        return false;
                }
            }
        }
        return true;
    }
}
