package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorTop extends CommandBase{
    Elevator elevator;
    
    public ElevatorTop(Elevator elevator){
        this.elevator = elevator;
        addRequirements(elevator); 
    }

    @Override
    public void initialize() {
        elevator.setPercent(1);
    }
    
    @Override
    public boolean isFinished() {
        return elevator.topLimitHit();
    }
    @Override
    public void end(boolean interrupted) {
        elevator.setPercent(0);
    }
    
}
