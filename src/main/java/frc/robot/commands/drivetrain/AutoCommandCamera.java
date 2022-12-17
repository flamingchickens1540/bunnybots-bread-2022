 package frc.robot.commands.drivetrain;


 import com.pathplanner.lib.PathPlanner;
 import com.pathplanner.lib.PathPlannerTrajectory;
 import com.pathplanner.lib.commands.PPRamseteCommand;
 import com.pathplanner.lib.PathConstraints;

 import edu.wpi.first.math.controller.PIDController;
 import edu.wpi.first.math.geometry.Pose2d;
 import edu.wpi.first.math.geometry.Rotation2d;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import edu.wpi.first.wpilibj2.command.InstantCommand;
 import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.elevator.Eject1PerZone;
import frc.robot.commands.elevator.Elevator;
import frc.robot.commands.intake.Intake;
import frc.robot.utils.ChickenPhotonCamera;

 public class AutoCommandCamera extends SequentialCommandGroup{
    public AutoCommandCamera(Drivetrain drivetrain, Intake intake, Elevator elevator, ChickenPhotonCamera camera){
        addRequirements(drivetrain);
        addRequirements(intake);
        addRequirements(elevator);

        //  drivetrain.zeroHeading();

         
        //  Pose2d start = new Pose2d(0,0,new Rotation2d(0));
        //  Pose2d end = new Pose2d(3,0,new Rotation2d(180));

        //  SmartDashboard.putNumber("pose/startPose", start.getRotation().getDegrees());
        //  SmartDashboard.putNumber("pose/endPose", end.getRotation().getDegrees());

         // Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
         // // Start at the origin facing the +X direction
         // start,
         // List.of(new Translation2d(1, 0), new Translation2d(1, -1), new Translation2d(2, -1), new Translation2d(2, 0), new Translation2d(1, 0)),
         // // List.of(),
         // end,
         // // Pass config
         // RamseteConfig.trajectoryConfig
         //  );

        PathPlannerTrajectory trajectory = PathPlanner.loadPath("testPath2", new PathConstraints(2, 1));
        drivetrain.resetOdometry(trajectory.getInitialPose());
        // trajectory.getInitialPose().getRotation();

        PPRamseteCommand ramseteCommand = new PPRamseteCommand(
            trajectory,
            drivetrain::getPose,
            RamseteConfig.ramseteController,
            RamseteConfig.feedForward,
            RamseteConfig.kDriveKinematics,
            drivetrain::getWheelSpeeds,
            new PIDController(Constants.KP_DRIVE_VEL, 0, 0),
            new PIDController(Constants.KP_DRIVE_VEL, 0, 0),
            drivetrain::setVolts,
            drivetrain
        );


        drivetrain.resetOdometry(trajectory.getInitialPose());
        

        addCommands(
            new InstantCommand(()-> drivetrain.brakeOn()),
            ramseteCommand,
            new InstantCommand(()-> drivetrain.setVolts(0, 0)),
            new WaitCommand(0.4),
            new TurnToAprilTag(drivetrain, camera),
            new MoveABit(drivetrain, 10, 0.2),
            //  new InstantCommand(()-> SmartDashboard.putNumber("navx/endRotation", drivetrain.getPose().getRotation().getDegrees())),
            new Eject1PerZone(intake, elevator, drivetrain)
        );
    }
    

    
 }
