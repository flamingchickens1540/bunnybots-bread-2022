package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorPID extends CommandBase {
    private final Elevator elevator;
    private final double setpoint;
    private final boolean isMotionMagic;

    public ElevatorPID(Elevator elevator, double setpoint, boolean isMotionMagic) {
        this.elevator = elevator;
        this.setpoint = setpoint;
        this.isMotionMagic = isMotionMagic;
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        if (isMotionMagic) elevator.setPositionMotionMagic(setpoint);
        else elevator.setPosition(setpoint);
    }

    @Override
    public boolean isFinished() {
        return false;//Math.abs(setpoint - elevator.getPosition()) <= 50;
    }

    @Override
    public void end(boolean isInterrupted) {
        elevator.setPercent(0);
    }
}
