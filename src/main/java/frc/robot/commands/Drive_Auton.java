package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Intake;


//import java.util.function.Double;

public class Drive_Auton extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private final Double m_translationX;
    private final Double m_translationY;
    private final Double m_rotation;
    private final Double m_distance;
    private final Double m_angle;
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private Boolean EndCommand = false;

    

    public Drive_Auton(DrivetrainSubsystem drivetrainSubsystem,Double translationX, Double translationY, Double angle, Double distance)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationX = translationX;
        this.m_translationY = translationY;
        this.m_rotation = 0.3;
        this.m_distance = distance;
        this.m_angle = angle;

        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        
        EndCommand = false;
        m_drivetrainSubsystem.zeroDriveEncoders();
        m_drivetrainSubsystem.zeroGyroscope();
      }

    @Override
    public void execute() 
    {
        SmartDashboard.putNumber("Distance",m_drivetrainSubsystem.Distance);
        double translationXPercent = m_translationX;
        double translationYPercent = m_translationY;
        double rotationPercent = m_rotation;
        rotationPercent = 0;

        
      if(m_drivetrainSubsystem.Distance>m_distance)
      {
        translationXPercent = 0;
        translationYPercent = 0;
        if(m_angle > m_drivetrainSubsystem.getGyroscopeAngle())
        {
          EndCommand=true;
        }
      }
      if(m_angle > m_drivetrainSubsystem.getGyroscopeAngle())
      {
        rotationPercent = 0;
      }
  
      m_drivetrainSubsystem.drive(
        ChassisSpeeds.fromFieldRelativeSpeeds
        (
          translationXPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
          translationYPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
          rotationPercent * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
          m_drivetrainSubsystem.getGyroscopeRotation()
        ));
      
    }

    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.drive(ChassisSpeeds.fromFieldRelativeSpeeds(0.0, 0.0, 0.0, m_drivetrainSubsystem.getGyroscopeRotation()));
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      return EndCommand;
    }
}