package com.example.library.render;

import android.content.Context;
import android.util.SparseArray;

import com.example.library.render.animal.FishLoadingRenderer;
import com.example.library.render.animal.GhostsEyeLoadingRenderer;
import com.example.library.render.circle.jump.CollisionLoadingRenderer;
import com.example.library.render.circle.jump.DanceLoadingRenderer;
import com.example.library.render.circle.jump.GuardLoadingRenderer;
import com.example.library.render.circle.jump.SwapLoadingRenderer;
import com.example.library.render.circle.rotate.GearLoadingRenderer;
import com.example.library.render.circle.rotate.LevelLoadingRenderer;
import com.example.library.render.circle.rotate.MaterialLoadingRenderer;
import com.example.library.render.circle.rotate.WhorlLoadingRenderer;
import com.example.library.render.goods.BalloonLoadingRenderer;
import com.example.library.render.goods.WaterBottleLoadingRenderer;
import com.example.library.render.scenery.DayNightLoadingRenderer;
import com.example.library.render.scenery.ElectricFanLoadingRenderer;
import com.example.library.render.shapechange.CircleBroodLoadingRenderer;
import com.example.library.render.shapechange.CoolWaitLoadingRenderer;

import java.lang.reflect.Constructor;


public final class LoadingRendererFactory {
    private static final SparseArray<Class<? extends LoadingRenderer>> LOADING_RENDERERS = new SparseArray<>();

    static {
        //circle rotate
        LOADING_RENDERERS.put(0, MaterialLoadingRenderer.class);
        LOADING_RENDERERS.put(1, LevelLoadingRenderer.class);
        LOADING_RENDERERS.put(2, WhorlLoadingRenderer.class);
        LOADING_RENDERERS.put(3, GearLoadingRenderer.class);
        //circle jump
        LOADING_RENDERERS.put(4, SwapLoadingRenderer.class);
        LOADING_RENDERERS.put(5, GuardLoadingRenderer.class);
        LOADING_RENDERERS.put(6, DanceLoadingRenderer.class);
        LOADING_RENDERERS.put(7, CollisionLoadingRenderer.class);
        //scenery
        LOADING_RENDERERS.put(8, DayNightLoadingRenderer.class);
        LOADING_RENDERERS.put(9, ElectricFanLoadingRenderer.class);
        //animal
        LOADING_RENDERERS.put(10, FishLoadingRenderer.class);
        LOADING_RENDERERS.put(11, GhostsEyeLoadingRenderer.class);
        //goods
        LOADING_RENDERERS.put(12, BalloonLoadingRenderer.class);
        LOADING_RENDERERS.put(13, WaterBottleLoadingRenderer.class);
        //shape change
        LOADING_RENDERERS.put(14, CircleBroodLoadingRenderer.class);
        LOADING_RENDERERS.put(15, CoolWaitLoadingRenderer.class);
    }

    private LoadingRendererFactory() {
    }

    public static LoadingRenderer createLoadingRenderer(Context context, int loadingRendererId) throws Exception {
        Class<?> loadingRendererClazz = LOADING_RENDERERS.get(loadingRendererId);
        Constructor<?>[] constructors = loadingRendererClazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes != null
                    && parameterTypes.length == 1
                    && parameterTypes[0].equals(Context.class)) {
                constructor.setAccessible(true);
                return (LoadingRenderer) constructor.newInstance(context);
            }
        }

        throw new InstantiationException();
    }
}
