package frc.robot.commands.drivetrain;

import com.kauailabs.navx.frc.AHRS;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AprilTestGroup extends SequentialCommandGroup{
    public AprilTestGroup(Drivetrain drivetrain, PhotonCamera camera, AHRS navx){
        addCommands(new TurnToAprilTag(drivetrain, camera, navx), new DriveToAprilTag(drivetrain, camera));
    }   
}
