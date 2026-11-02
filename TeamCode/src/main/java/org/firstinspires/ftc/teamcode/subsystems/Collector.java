package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Collector {
    DcMotor intakeMotor;
    CRServo leftFlowerServo;
    CRServo rightFlowerServo;

    public Collector(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
        leftFlowerServo = hardwareMap.get(CRServo.class, "left_intake_servo");
        rightFlowerServo = hardwareMap.get(CRServo.class, "right_intake_servo");

        intakeMotor.setPower(0.0);
        leftFlowerServo.setPower(0.0);
        rightFlowerServo.setPower(0.0);
    }

    public class Intake implements Action {
        private final int seconds;
        public Intake(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intakeMotor.setPower(1.0);
            new SleepAction(seconds).run(telemetryPacket);
            intakeMotor.setPower(0.0);
            return false;
        }
    }

    public Action intake(int duration) {
        return new Intake(duration);
    }

    public class IntakeFlower implements Action {
        private final int seconds;
        public IntakeFlower(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            leftFlowerServo.setPower(1.0);
            rightFlowerServo.setPower(1.0);
            new SleepAction(seconds).run(telemetryPacket);
            leftFlowerServo.setPower(0.0);
            rightFlowerServo.setPower(0.0);
            return false;
        }
    }

    public Action intakeFlower(int duration) {
        return new ParallelAction(
                new IntakeFlower(duration),
                intake(duration)
        );
    }
}
