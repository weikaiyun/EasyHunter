package com.quinn.hunter.plugin.linelog;

import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.quinn.hunter.plugin.linelog.bytecode.LinelogWeaver;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;

import org.gradle.api.Project;

import java.io.IOException;


/**
 * Created by Quinn on 15/09/2018.
 */
public final class LinelogHunterTransform extends HunterTransform {

    private final Project project;
    private LinelogHunterExtension linelogHunterExtension;

    public LinelogHunterTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("linelogHunterExt", LinelogHunterExtension.class);
        this.bytecodeWeaver = new LinelogWeaver();
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        linelogHunterExtension = (LinelogHunterExtension) project.getExtensions().getByName("linelogHunterExt");
        super.transform(transformInvocation);
    }

    @Override
    protected RunVariant getRunVariant() {
        return linelogHunterExtension.runVariant;
    }

}
