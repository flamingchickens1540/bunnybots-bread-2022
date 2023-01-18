// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.AutoCommand;
import frc.robot.commands.drivetrain.AutoCommandCamera;
import frc.robot.commands.drivetrain.MoveABit;
import frc.robot.commands.drivetrain.Drivetrain;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.commands.elevator.Eject1PerZone;
import frc.robot.commands.elevator.Elevator;
import frc.robot.commands.elevator.ElevatorBottom;
import frc.robot.commands.elevator.ElevatorCommand;
import frc.robot.commands.elevator.ElevatorTop;
import frc.robot.commands.intake.Eject;
import frc.robot.commands.intake.Intake;
import frc.robot.commands.intake.IntakeCommand;
import frc.robot.utils.ChickenPhotonCamera;
import frc.robot.utils.FlamingPigeon2;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final FlamingPigeon2 pigeon = new FlamingPigeon2(7);
  private final XboxController pilot = new XboxController(0);
  private final XboxController copilot = new XboxController(1);
  private final Drivetrain drivetrain = new Drivetrain(pigeon);
  private final Intake intake = new Intake();
  private final Elevator elevator = new Elevator();
  private final ChickenPhotonCamera camera = new ChickenPhotonCamera("Microsoft_LifeCam_HD-3000");


  private final AutoCommand autoCommand = new AutoCommand(drivetrain, intake, elevator);
  private final AutoCommandCamera autoCommandCamera = new AutoCommandCamera(drivetrain, intake, elevator, camera) ;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drivetrain.setDefaultCommand(new TankDrive(pilot, drivetrain, elevator));
    elevator.setDefaultCommand(new ElevatorCommand(copilot, elevator));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(copilot, XboxController.Button.kA.value)
    .whileActiveOnce(new IntakeCommand(intake, 0.6));
    new JoystickButton(copilot, XboxController.Button.kB.value)
    .whileActiveOnce(new IntakeCommand(intake, -0.49));
    // new JoystickButton(copilot, XboxController.Button.kX.value)
    // .whenPressed(new ElevatorBottom(elevator));
    new JoystickButton(copilot, XboxController.Button.kY.value)
    .whenPressed(new ElevatorTop(elevator));
    new JoystickButton(copilot, XboxController.Button.kRightBumper.value)
    .whenPressed(new Eject(intake, 8, -0.5));
    // new JoystickButton(copilot, XboxController.Button.kLeftBumper.value)
    // .whenPressed(new MoveABit(drivetrain, 10, -0.2));
    // new JoystickButton(pilot, XboxController.Button.kRightBumper.value)
    // .whileActiveOnce(new Eject1PerZone(intake, elevator, drivetrain));
    // new JoystickButton(pilot, XboxController.Button.kLeftBumper.value)
    // .whenPressed(new InstantCommand(drivetrain::toggleBrake,drivetrain));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_autoCommand;
    return new AutoCommand(drivetrain, intake, elevator);
    // return autoCommand;
    // return new 
    // return new Eject1PerZone(intake, elevator, drivetrain);
  }
}
