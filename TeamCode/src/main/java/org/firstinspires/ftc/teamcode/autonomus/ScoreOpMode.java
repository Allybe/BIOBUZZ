package org.firstinspires.ftc.teamcode.autonomus;


import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TimeTrajectory;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous(name="Scoring Test", group="AUTO")
public class ScoreOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
        waitForStart();
        Shooter shooter = robot.getShooter();
        PinpointDrive drive = robot.getDrive();
        Actions.runBlocking(
                new SequentialAction(
                        shooter.chargeAndShoot(1440),
                        shooter.chargeAndShoot(1440),
                        shooter.chargeAndShoot(1440),
                        shooter.chargeAndShoot(1440)
                )
        );
    }
}
//17.70 length
//17.46 width