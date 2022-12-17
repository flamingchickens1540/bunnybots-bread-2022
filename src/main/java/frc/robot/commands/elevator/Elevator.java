package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase{
    private final TalonFX elevatorMotor = new TalonFX(Constants.ElevatorConstants.FALCON);
    // private 
    public Elevator(){
        // elevatorMotor.set(Mode, demand);
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
        elevatorMotor.setInverted(true);
        elevatorMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,LimitSwitchNormal.NormallyClosed);
        elevatorMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,LimitSwitchNormal.NormallyClosed);
        elevatorMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 30, 0));
        // elevatorMotor

    }
    

    public void setPercent(double percent) {
        elevatorMotor.set(ControlMode.PercentOutput, percent);
    }

    public boolean topLimitHit(){
        return elevatorMotor.isFwdLimitSwitchClosed() == 1?false:true;
    }

    public boolean bottomLimitHit(){
        return elevatorMotor.isRevLimitSwitchClosed() == 1?false:true;
    }

    @Override
    public void periodic() {
        // System.out.println(elevatorMotor.getSelectedSensorPosition());
        if(elevatorMotor.isFwdLimitSwitchClosed() == 1){
            elevatorMotor.setSelectedSensorPosition(352000);
        }
        else if(elevatorMotor.isRevLimitSwitchClosed() == 1){
            elevatorMotor.setSelectedSensorPosition(0);
        }
        SmartDashboard.putNumber("bread/elevator/position", elevatorMotor.getSelectedSensorPosition());
    }

    public double getPosition(){
        return elevatorMotor.getSelectedSensorPosition();
    }


    // @Override
    // public void periodic() {
    //     // System.out.println(topLimit());
    // }

}
