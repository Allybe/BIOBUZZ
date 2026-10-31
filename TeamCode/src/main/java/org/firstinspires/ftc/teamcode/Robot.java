package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

public class Robot {
    private Shooter shooter;
    private Collector collector;
    private PinpointDrive drive;

    public Robot(HardwareMap hardwareMap, Pose2d startingPose) {
        shooter = new Shooter(hardwareMap);
        collector = new Collector(hardwareMap);
        drive = new PinpointDrive(hardwareMap, startingPose);
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Collector getCollector() {
        return collector;
    }

    public PinpointDrive getDrive() {
        return drive;
    }
}
