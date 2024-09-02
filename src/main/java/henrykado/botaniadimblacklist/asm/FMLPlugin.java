package henrykado.botaniadimblacklist.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@IFMLLoadingPlugin.Name()
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({"henrykado.botaniadimblacklist.asm", "henrykado.botaniadimblacklist"})
public class FMLPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {ASMTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null; //ModContainer.class.getName();
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
