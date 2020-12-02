package com.quinn.hunter.plugin.debug;

import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.quinn.hunter.plugin.debug.bytecode.DebugWeaver;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;

import org.gradle.api.Project;

import java.io.IOException;

/**
 * Created by Quinn on 16/09/2018.
 */
public class DebugHunterTransform extends HunterTransform {

    private final Project project;
    private DebugHunterExtension debugHunterExtension;

    public DebugHunterTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("debugHunterExt", DebugHunterExtension.class);
        this.bytecodeWeaver = new DebugWeaver();
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        debugHunterExtension = (DebugHunterExtension) project.getExtensions().getByName("debugHunterExt");
        super.transform(transformInvocation);
    }

    @Override
    protected RunVariant getRunVariant() {
        return debugHunterExtension.runVariant;
    }

    @Override
    protected boolean inDuplicatedClassSafeMode() {
        return debugHunterExtension.duplcatedClassSafeMode;
    }
}