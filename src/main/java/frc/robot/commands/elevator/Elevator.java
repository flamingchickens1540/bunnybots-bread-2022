package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase{
    TalonFX elevatorMotor = new TalonFX(Constants.ElevatorConstants.FALCON);

    public Elevator(){
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
    }
    

    public void setPercent(double percent) {
        elevatorMotor.set(ControlMode.PercentOutput, percent*0.1);
    }

}
