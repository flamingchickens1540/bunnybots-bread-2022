package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.FlamingPigeon2;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

public class Drivetrain extends SubsystemBase{
    
    private final TalonFX rightFront = new TalonFX(Constants.DrivetrainConstants.RIGHT_FRONT);
    private final TalonFX rightBack = new TalonFX(Constants.DrivetrainConstants.RIGHT_BACK);
    private final TalonFX leftFront = new TalonFX(Constants.DrivetrainConstants.LEFT_FRONT);
    private final TalonFX leftBack = new TalonFX(Constants.DrivetrainConstants.LEFT_BACK);
    private final FlamingPigeon2 pigeon;
    private final DifferentialDriveOdometry odometry;
    private boolean brakeTrue;

    public Drivetrain(FlamingPigeon2 pigeon){
        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        leftFront.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
        brakeTrue = true;
        
        this.pigeon = pigeon;
        odometry = new DifferentialDriveOdometry(pigeon.getRotation2d());
    }

    @Override
    public void periodic() {
        odometry.update(
            pigeon.getRotation2d(), 
            leftFront.getSelectedSensorPosition()/Constants.ENCODER_TICKS_PER_METER,
            rightFront.getSelectedSensorPosition()/Constants.ENCODER_TICKS_PER_METER
        );
    }
    
    public void setPercent(double leftPercent, double rightPercent){
        leftFront.set(ControlMode.PercentOutput, leftPercent);
        rightFront.set(ControlMode.PercentOutput, -rightPercent);
    }

    public Pose2d getPose(){
        return odometry.getPoseMeters();
    }

    public void setVolts(double leftVolts, double rightVolts){
        leftFront.set(ControlMode.PercentOutput, leftVolts/12);
        rightFront.set(ControlMode.PercentOutput, rightVolts/12);
    }
    public DifferentialDriveWheelSpeeds getWheelSpeeds(){
        return new DifferentialDriveWheelSpeeds(
                leftFront.getSelectedSensorVelocity()*10/Constants.ENCODER_TICKS_PER_METER,
                rightFront.getSelectedSensorVelocity()*10/Constants.ENCODER_TICKS_PER_METER
        );
    }

    public void resetOdometry(Pose2d pose){
        leftFront.setSelectedSensorPosition(0);
        rightFront.setSelectedSensorPosition(0);
        zeroHeading();
        odometry.resetPosition(pose, new Rotation2d(-Math.PI/2));
        // new Rotation2d()
    }

    public void zeroHeading(){
        pigeon.setYaw(0);
    }    

    public TalonFXSensorCollection getLeftSensors(){
        return leftFront.getSensorCollection();
    }
    public TalonFXSensorCollection getRightSensors(){
        return rightFront.getSensorCollection();
    }

    public void brakeOn(){
        leftFront.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
    }
    public void brakeOff(){
        leftFront.setNeutralMode(NeutralMode.Coast);
        rightFront.setNeutralMode(NeutralMode.Coast);
    }

    public void toggleBrake(){
        brakeTrue = !brakeTrue;
        leftFront.setNeutralMode(brakeTrue?NeutralMode.Brake:NeutralMode.Coast);
        rightFront.setNeutralMode(brakeTrue?NeutralMode.Brake:NeutralMode.Coast);
    }

    public double getYaw(){
        return pigeon.getYaw();
    }
}
