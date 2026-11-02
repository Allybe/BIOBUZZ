package org.firstinspires.ftc.teamcode.autonomus;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous(name="Scoring Test", group="AUTO")
public class ScoreOpMode extends LinearOpMode {
    private Robot robot;
    private Action startToScoreTrajectory;

    private void initialization() {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
        startToScoreTrajectory = robot.getDrive().actionBuilder(new Pose2d(-60, 11, 0))
                .splineTo(new Vector2d(-12, -58), 0.0)
                .turnTo(90)
                .build();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();
        Shooter shooter = robot.getShooter();
        PinpointDrive drive = robot.getDrive();
        Actions.runBlocking(
                new SequentialAction(
                        startToScoreTrajectory,
                        new SequentialAction(
                                shooter.chargeAndShoot(1440),
                                shooter.chargeAndShoot(1440),
                                shooter.chargeAndShoot(1440),
                                shooter.chargeAndShoot(1440)
                        )
                )
        );
    }
}
//17.70 length
//17.46 width