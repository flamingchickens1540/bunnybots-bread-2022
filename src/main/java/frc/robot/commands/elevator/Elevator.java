package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

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
        elevatorMotor.config_kP(0, Constants.ElevatorConstants.KP);
        elevatorMotor.config_kI(0, Constants.ElevatorConstants.KI);
        elevatorMotor.config_kD(0, Constants.ElevatorConstants.KD);
        elevatorMotor.configMotionCruiseVelocity(Constants.ElevatorConstants.CRUISE_VELOCITY);
        elevatorMotor.configMotionAcceleration(Constants.ElevatorConstants.MAX_ACCEL);
        // elevatorMotor

    }
    

    public void setPercent(double percent) {
        elevatorMotor.set(ControlMode.PercentOutput, percent);
    }

    public boolean topLimitHit(){
        return elevatorMotor.isFwdLimitSwitchClosed() != 1;
    }

    public boolean bottomLimitHit(){
        return elevatorMotor.isRevLimitSwitchClosed() != 1;
    }

    public void setPosition(double pos) {
        elevatorMotor.set(ControlMode.Position, pos);
    }

    public void setPositionMotionMagic(double pos) {
        elevatorMotor.set(ControlMode.MotionMagic, pos);
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
        System.out.println(bottomLimitHit());
    }

    public double getPosition(){
        return elevatorMotor.getSelectedSensorPosition();
    }
}
