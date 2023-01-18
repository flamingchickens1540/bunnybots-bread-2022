package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase{
    Elevator elevator;
    XboxController controller;
    public ElevatorCommand(XboxController controller, Elevator elevator){
        this.elevator = elevator;
        this.controller = controller;
        addRequirements(elevator);
    }


    public void execute() {
        double up = controller.getRightTriggerAxis();
        double down = -controller.getLeftTriggerAxis() * 0.5;
        elevator.setPercent(up + down);
    }

}
