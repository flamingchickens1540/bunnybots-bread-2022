// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DrivetrainConstants{
        public static final int LEFT_FRONT = 1;
        public static final int LEFT_BACK = 2;
        public static final int RIGHT_FRONT = 3;
        public static final int RIGHT_BACK = 4;
    }

    public static final class IntakeConstants{
        public static final int SPARK = 5;
    }

    public static final class ElevatorConstants{
        public static final int FALCON = 6;
        public static final double KP = 0.0086182;
        public static final double KI = 0.0;
        public static final double KD = 0.48225;
        public static final double CRUISE_VELOCITY = 20_000;
        public static final double MAX_ACCEL = 45_000;
        public static final double ENTODER_KICKS_PER_INCH = 8192;
    }

    public static final double ENCODER_TICKS_PER_METER = 49866;

    public static final double KS_VOLTS = 0.64995;
    public static final double KV_VOLT_SECONDS_PER_METER = 2.3287;
    public static final double KA_VOLT_SECONDS_SQUARED_PER_METER = 0.22946;
    public static final double KP_DRIVE_VEL = 2.9224;

    public static final double K_TRACKWIDTH_METERS = 0.771;
}
