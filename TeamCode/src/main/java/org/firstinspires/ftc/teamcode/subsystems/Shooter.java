
package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Shooter {
    private DcMotorEx shooterMotor;
    private CRServo feederServo;

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, "flywheel_motor");
        feederServo = hardwareMap.get(CRServo.class, "intake_servo");

        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(2.0, 0.5, 0.0, 11.1));
        shooterMotor.setPower(0.0);

        feederServo.setDirection(DcMotorSimple.Direction.REVERSE);
        feederServo.setPower(0.0);
    }

    public class MaintainVelocity implements Action {
        private SleepAction timer;
        private double power;

        public MaintainVelocity(int duration, double power) {
            this.power = power;
            timer = new SleepAction(duration);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            shooterMotor.setPower(this.power);

            if (timer.run(packet)) {
                packet.put("Power Draw:", shooterMotor.getCurrent(CurrentUnit.MILLIAMPS));
                packet.put("Velocity:", shooterMotor.getVelocity());
                return true;
            }

            shooterMotor.setPower(0.0);
            return false;
        }
    }

    public Action maintainVelocity(int duration, double power) {
        return new MaintainVelocity(duration, power);
    }

    public void maintainVelocity(boolean enabled) {
        double speed = 1450;
        if (enabled) {
            shooterMotor.setVelocity(speed);
        } else {
            shooterMotor.setPower(0.0);
        }
    }

    public class RunFeeder implements Action {
        private final SleepAction timer;

        public RunFeeder(int duration) {
            timer = new SleepAction(duration);
            feederServo.setPower(0.0);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            feederServo.setPower(1.0);

            if (timer.run(packet)) {
                return true;
            }

            feederServo.setPower(0.0);
            return false;
        }
    }

    public Action runFeeder(int duration) {
        return new RunFeeder(duration);
    }

    public class ChargeAndShoot implements Action {
        public double speed;
        public ChargeAndShoot(double ticksPerSecond) {
            this.speed = ticksPerSecond;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            telemetryPacket.put("Power Draw:", shooterMotor.getCurrent(CurrentUnit.MILLIAMPS));
            telemetryPacket.put("Velocity:", shooterMotor.getVelocity());
            shooterMotor.setVelocity(speed);
            if (Math.abs(shooterMotor.getVelocity() - speed) < 20) {
                feederServo.setPower(1.0);
            }

            if (feederServo.getPower() == 1.0) {
                if (Math.abs(shooterMotor.getVelocity() - speed) > 200) {
                    shooterMotor.setPower(0.0);
                    feederServo.setPower(0.0);
                    return false;
                }
                return true;
            }

            return true;
        }
    }

    public Action chargeAndShoot(double ticksPerSecond) {
        return new ChargeAndShoot(ticksPerSecond);
    }

    public void chargeAndShoot(boolean enabled) {
        double speed = 1450.0;
        if (enabled) {
            shooterMotor.setVelocity(speed);
            if (Math.abs(shooterMotor.getVelocity() - speed) < 20) {
                feederServo.setPower(1.0);
            } else {
                feederServo.setPower(0.0);
            }

            if (feederServo.getPower() == 1.0) {
                if (Math.abs(shooterMotor.getVelocity() - speed) > 200) {
                    shooterMotor.setPower(0.0);
                    feederServo.setPower(0.0);
                }
            }
        } else {
            shooterMotor.setPower(0.0);
            feederServo.setPower(0.0);
        }
    }
 }
