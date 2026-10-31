package org.firstinspires.ftc.teamcode.autonomus;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

public class ScoreOpMode extends LinearOpMode {
    private Robot robot;

    public ScoreOpMode() {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
    }

    @Override
    public void runOpMode() throws InterruptedException {
        return;
    }
}
