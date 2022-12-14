package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorBottom extends CommandBase{
    Elevator elevator;
    
    public ElevatorBottom(Elevator elevator){
        this.elevator = elevator;
        addRequirements(elevator); 
    }

    @Override
    public void initialize() {
        elevator.setPercent(-1);
    }
    
    @Override
    public boolean isFinished() {
        return elevator.bottomLimitHit();
    }
    @Override
    public void end(boolean interrupted) {
        elevator.setPercent(0);
    }
    
}
