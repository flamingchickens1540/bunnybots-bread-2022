package frc.robot.utils;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;

public class FlamingPigeon2 extends Pigeon2{

    public FlamingPigeon2(int deviceNumber) {
        super(deviceNumber);
    }

    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(-this.getYaw());
    }
    
}
