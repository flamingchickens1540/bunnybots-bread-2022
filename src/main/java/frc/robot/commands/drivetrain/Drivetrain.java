package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

public class Drivetrain extends SubsystemBase{
    
    private final TalonFX rightFront = new TalonFX(Constants.DrivetrainConstants.RIGHT_FRONT);
    private final TalonFX rightBack = new TalonFX(Constants.DrivetrainConstants.RIGHT_BACK);
    private final TalonFX leftFront = new TalonFX(Constants.DrivetrainConstants.LEFT_FRONT);
    private final TalonFX leftBack = new TalonFX(Constants.DrivetrainConstants.LEFT_BACK);

    public Drivetrain(){
        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        leftFront.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
    }
    
    
    public void setPercent(double leftPercent, double rightPercent){
        leftFront.set(ControlMode.PercentOutput, leftPercent);
        rightFront.set(ControlMode.PercentOutput, -rightPercent);
    }

    public TalonFXSensorCollection getLeftSensors(){
        return leftFront.getSensorCollection();
    }
    public TalonFXSensorCollection getRightSensors(){
        return leftFront.getSensorCollection();
    }
}
