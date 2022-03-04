package dev.mrsterner.bewitchmentplus.client.renderer;

import dev.mrsterner.bewitchmentplus.BewitchmentPlus;
import dev.mrsterner.bewitchmentplus.client.BewitchmentPlusClient;
import dev.mrsterner.bewitchmentplus.client.shader.BWPShader;
import dev.mrsterner.bewitchmentplus.common.entity.RuneEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;

import static dev.mrsterner.bewitchmentplus.common.utils.RenderHelper.renderLayer;

public class RuneEntityRenderer extends EntityRenderer<RuneEntity> {
    public RuneEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(RuneEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider provider, int light) {
        Shader shader = BWPShader.rune();
        if (shader != null) {
            //shader.getUniformOrDefault("Disfiguration").set((0.025F + 1 * ((1F - 0.15F) / 20F)) / 2F);
        }
        double ticks = (BewitchmentPlusClient.ClientTickHandler.ticksInGame + tickDelta) * 0.5;
        renderRing(true,10, ticks, entity, matrices, provider, light);
        //renderRing(false,20, ticks, entity, matrices, provider, light);
    }

    public void renderRing(boolean clockwise,int salt, double ticks, RuneEntity entity, MatrixStack matrices, VertexConsumerProvider provider, int light){
        matrices.push();
        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion((float) Math.sin(ticks/100) * salt));
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion((float) Math.cos(ticks/100) * salt));
        if (true) {
            float v = 1F / 8F;
            final float modifier = 6F;
            final float rotationModifier = 0.25F;
            final float radiusBase = 200.0F;//(float) Math.exp(Math.sin(ticks/200)) * 100;
            final float radiusMod = 0.1F;
            int runes = (int) Math.floor(radiusBase/2);
            float offsetPerRune = 360.0F / runes;
            matrices.push();
            matrices.translate(-0.05F, -0.5F, 0F);
            matrices.scale(v, v, v);
            for (int i = 0; i < runes; i++) {
                float offset = offsetPerRune * i;
                float deg = (int) (ticks / rotationModifier % 360F + offset);
                float rad = deg * (float) Math.PI / 180F;
                float radiusX = (float) (radiusBase + radiusMod * Math.sin(ticks / modifier));
                float radiusZ = (float) (radiusBase + radiusMod * Math.cos(ticks / modifier));
                float x = (float) (radiusX * Math.cos(rad));
                float z = (float) (radiusZ * Math.sin(rad));
                matrices.push();
                matrices.translate((clockwise ? x : -x), 0, (clockwise ? z : -z));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((clockwise ? -deg : deg) + 100));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
                matrices.scale(20,20,20);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                renderLayer(getTexture(i % 5 + 1), matrix4f, provider, 1,1,light, OverlayTexture.DEFAULT_UV, new float[]{1F, 1F, 1F, 1F});
                //matrices.scale(1.5F,1.5F,1.5F);
                //matrices.translate(0,1,0);
                //renderPortalLayer(getTexture(i % 5 + 1), matrix4f, provider, 1F,1F,light, OverlayTexture.DEFAULT_UV, new float[]{1F, 1F, 1F, 0.5F});
                if(radiusBase % 80 >= 50){
                    //entity.world.addParticle(ParticleTypes.DRIPPING_HONEY, entity.getX()+(x/10),entity.getY(),entity.getZ()+(z/10),0,0,0);
                }
                matrices.pop();
            }
            matrices.pop();
        }
        matrices.pop();
    }

    public Identifier getTexture(int j) {
        return new Identifier(BewitchmentPlus.MODID, "textures/block/rune_" + j + ".png");
    }

    @Override
    public boolean shouldRender(RuneEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

    @Override
    public Identifier getTexture(RuneEntity entity) {
        return null;
    }
 }
