package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.elevator.Elevator;

public class TankDrive extends CommandBase{
    private XboxController controller;
    private Drivetrain drivetrain;
    private Elevator elevator;
    public TankDrive(XboxController controller, Drivetrain drivetrain, Elevator elevator){
        this.controller = controller;
        this.drivetrain = drivetrain;
        this.elevator = elevator;
        addRequirements(drivetrain);
    }    

    @Override
    public void execute() {
        double left = -Math.pow(controller.getLeftY(),3) * 0.5;
        double right = -Math.pow(controller.getRightY(),3) * 0.5;
        double forward = Math.pow(controller.getRightTriggerAxis(),3) * 0.5;
        double backward = -Math.pow(controller.getLeftTriggerAxis(),3) * 0.25;
        // double num = (349000 - elevator.getPosition())/349000 * 3 + 0.4;
        // num = num>1?1:num;
        left += forward + backward;
        right += forward + backward;
        drivetrain.setPercent(left, right);
    }


}
