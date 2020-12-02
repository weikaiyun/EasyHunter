package com.quinn.hunter.plugin.timing;

import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.quinn.hunter.plugin.timing.bytecode.TimingWeaver;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;

import org.gradle.api.Project;

import java.io.IOException;

/**
 * Created by quinn on 07/09/2018
 */
public final class TimingHunterTransform extends HunterTransform {

    private final Project project;
    private TimingHunterExtension timingHunterExtension;

    public TimingHunterTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("timingHunterExt", TimingHunterExtension.class);
        this.bytecodeWeaver = new TimingWeaver();
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        timingHunterExtension = (TimingHunterExtension) project.getExtensions().getByName("timingHunterExt");
        bytecodeWeaver.setExtension(timingHunterExtension);
        super.transform(transformInvocation);
    }

    protected RunVariant getRunVariant() {
        return timingHunterExtension.runVariant;
    }

    @Override
    protected boolean inDuplicatedClassSafeMode() {
        return timingHunterExtension.duplicatedClassSafeMode;
    }
}
