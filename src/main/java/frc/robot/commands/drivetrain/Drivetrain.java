package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

public class Drivetrain extends SubsystemBase{
    
    private final TalonFX rightFront = new TalonFX(3);
    private final TalonFX rightBack = new TalonFX(4);
    private final TalonFX leftFront = new TalonFX(1);
    private final TalonFX leftBack = new TalonFX(2);

    public Drivetrain(){
        rightFront.set(ControlMode.PercentOutput, 0);
        rightBack.set(ControlMode.PercentOutput, 0);
        leftFront.set(ControlMode.PercentOutput, 0);
        leftBack.set(ControlMode.PercentOutput, 0);
        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        leftFront.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);
    }
    
    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        super.periodic();

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
