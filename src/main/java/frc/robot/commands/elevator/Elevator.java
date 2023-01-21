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
        SmartDashboard.putNumber("bread/elevator/kP", Constants.ElevatorConstants.KP);
        SmartDashboard.putNumber("bread/elevator/kI", Constants.ElevatorConstants.KI);
        SmartDashboard.putNumber("bread/elevator/kD", Constants.ElevatorConstants.KD);
        elevatorMotor.configMotionCruiseVelocity(Constants.ElevatorConstants.CRUISE_VELOCITY);
        elevatorMotor.configMotionAcceleration(Constants.ElevatorConstants.MAX_ACCEL);
        //elevatorMotor.configMotionSCurveStrength(0);
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
        System.out.println("hi");
        elevatorMotor.set(ControlMode.Position, pos);
    }

    public void setPositionMotionMagic(double pos) {
        elevatorMotor.set(ControlMode.MotionMagic, pos);
    }

    @Override
    public void periodic() {
        // System.out.println(elevatorMotor.getSelectedSensorPosition());
        if(bottomLimitHit()){
            elevatorMotor.setSelectedSensorPosition(0);
        }
        else if(topLimitHit()){
            elevatorMotor.setSelectedSensorPosition(352_000);
        }
        SmartDashboard.putNumber("bread/elevator/position", elevatorMotor.getSelectedSensorPosition());
//        if (bottomLimitHit()) System.out.println("bottom limit hit");
        elevatorMotor.config_kP(0, SmartDashboard.getNumber("bread/elevator/kP", Constants.ElevatorConstants.KP));
        elevatorMotor.config_kI(0, SmartDashboard.getNumber("bread/elevator/kI", Constants.ElevatorConstants.KI));
        elevatorMotor.config_kD(0, SmartDashboard.getNumber("bread/elevator/kD", Constants.ElevatorConstants.KD));
    }

    public double getPosition(){
        return elevatorMotor.getSelectedSensorPosition();
    }
}
