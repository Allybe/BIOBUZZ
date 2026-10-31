package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotor shooterMotor;
    private CRServo feederServo;
    public Shooter(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotor.class, "");
        feederServo = hardwareMap.get(CRServo.class, "");

        shooterMotor.setPower(0.0);
        feederServo.setPower(0.0);
    }

    public class MaintainVelocity implements Action {
        private double percentageOfVelocity;
        private int duration;
        public MaintainVelocity(int duration, double percentageOfVelocity) {
            this.percentageOfVelocity = percentageOfVelocity;
            this.duration = duration;
            shooterMotor.setPower(0.0);
            shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooterMotor.setPower(percentageOfVelocity);
            new SleepAction(duration).run(telemetryPacket);
            shooterMotor.setPower(0.0);
            return false;
        }
    }

    private Action maintainVelocity(int duration, double percentageOfMaxVelocity) {
        return new MaintainVelocity(duration, percentageOfMaxVelocity);
    }

    public class RunFeeder implements Action {
        private int duration;
        public RunFeeder(int duration) {
            this.duration = duration;
            feederServo.setPower(0.0);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            feederServo.setPower(1.0);
            new SleepAction(duration).run(telemetryPacket);
            feederServo.setPower(0.0);
            return false;
        }
    }

    public Action runFeeder(int duration) {
        return new RunFeeder(duration);
    }

}
