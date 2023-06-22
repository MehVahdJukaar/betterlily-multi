package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.client.model.CustomBakedModel;
import net.mehvahdjukaar.moonlight.api.client.model.ExtraModelData;
import net.mehvahdjukaar.moonlight.api.client.util.VertexUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterloggedLilyModel implements CustomBakedModel {

    private final BlockModelShaper blockModelShaper = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();

    @Override
    public List<BakedQuad> getBlockQuads(BlockState state, Direction side,
                                         RandomSource rand, RenderType renderType,
                                         ExtraModelData data) {
        BlockState mimic = data.get(WaterloggedLilyBlockEntity.MIMIC);
        List<BakedQuad> quads = new ArrayList<>();

        if (mimic != null && !mimic.isAir()) {

            BakedModel model = blockModelShaper.getBlockModel(mimic);
            List<BakedQuad> mimicQuads = model.getQuads(mimic, side, rand);

            for (BakedQuad q : mimicQuads) {
                int[] v = Arrays.copyOf(q.getVertices(), q.getVertices().length);
                VertexUtil.moveVertices(v, Direction.UP, 15/16f);

                quads.add(new BakedQuad(v, q.getTintIndex(), q.getDirection(), q.getSprite(), q.isShade()));
            }
            return quads;
        }

        return quads;
    }


    @Override
    public TextureAtlasSprite getBlockParticle(ExtraModelData data) {
        BlockState mimic = data.get(WaterloggedLilyBlockEntity.MIMIC);
        if (mimic != null && !mimic.isAir()) {

            BakedModel model = blockModelShaper.getBlockModel(mimic);
            try {
                return model.getParticleIcon();
            } catch (Exception ignored) {
            }

        }
        return blockModelShaper.getBlockModel(Blocks.LILY_PAD.defaultBlockState()).getParticleIcon();
    }


    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

    @Override
    public ItemTransforms getTransforms() {
        return ItemTransforms.NO_TRANSFORMS;
    }
}
