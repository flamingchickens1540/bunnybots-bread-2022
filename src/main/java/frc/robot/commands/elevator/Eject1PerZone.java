package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.MoveABit;
import frc.robot.commands.drivetrain.Drivetrain;
import frc.robot.commands.intake.Eject;
import frc.robot.commands.intake.Intake;

public class Eject1PerZone extends SequentialCommandGroup{
    public Eject1PerZone(Intake intake, Elevator elevator, Drivetrain drivetrain){
        addCommands(
            new Eject(intake, 7, -0.5),
            new MoveABit(drivetrain, 11, -0.2),
            // new WaitCommand(0.4),
            new ElevatorTop(elevator),
            new MoveABit(drivetrain, 10, 0.2),
            new Eject(intake, 14, -0.5),
            new WaitCommand(0.4),
            new ParallelCommandGroup(
                new ElevatorBottom(elevator),
                new MoveABit(drivetrain, 10, -0.2)
            ),
            new Eject(intake, 7, -0.5),
            new ParallelCommandGroup(
                new Eject(intake, 13, -0.5),
                new MoveABit(drivetrain, 30, -0.2)
            )
        );
    }
}
