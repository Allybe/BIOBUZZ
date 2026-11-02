package org.firstinspires.ftc.teamcode.teleoperated;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp(name = "Main", group = "TELEOP")
public class MainTeleOp extends OpMode {
    private Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, new Pose2d(0,0,0));
    }

    @Override
    public void loop() {
        PinpointDrive drive = robot.getDrive();
        Shooter shooter = robot.getShooter();
        Collector collector = robot.getCollector();

        drive.setDrivePowers(
                new PoseVelocity2d(
                        new Vector2d(gamepad1.left_stick_x, gamepad1.left_stick_y),
                        0
                )
        );
        shooter.chargeAndShoot(gamepad1.right_trigger_pressed);
        shooter.maintainVelocity(gamepad1.right_bumper);
        collector.intake(gamepad1.left_trigger_pressed);
        collector.intakeFlower(gamepad1.left_bumper);
     }
}
