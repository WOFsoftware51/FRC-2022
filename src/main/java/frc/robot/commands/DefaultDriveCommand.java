package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase 
{
    private final DrivetrainSubsystem m_drivetrainSubsystem;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private final DoubleSupplier m_rotationSupplier;
    private double rotation;
    private double Angle;
    private double Speed;
    PIDController AimPID= new PIDController(Constants.AIM_P, Constants.AIM_I, Constants.AIM_D);

    public DefaultDriveCommand
    (DrivetrainSubsystem drivetrainSubsystem, 
     DoubleSupplier translationXSupplier,
     DoubleSupplier translationYSupplier,
     DoubleSupplier rotationSupplier) 
   {

    this.m_drivetrainSubsystem = drivetrainSubsystem;
    this.m_translationXSupplier = translationXSupplier;
    this.m_translationYSupplier = translationYSupplier;
    this.m_rotationSupplier = rotationSupplier;

    addRequirements(drivetrainSubsystem);
   }

    @Override
    public void execute()
    {
        Angle = (m_drivetrainSubsystem.getAngle());
            Speed = (m_drivetrainSubsystem.getSpeed());
            if (Angle > 180)
            {
                Angle = Angle - 180;
                Speed = Speed * -1;
            }
            
        if (m_drivetrainSubsystem.Aim)
        {
            if(m_drivetrainSubsystem.tv == 1)
            {
                {

                    rotation = AimPID.calculate(this.m_drivetrainSubsystem.tx, 0)* DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
                }
            }
            else
            {
                rotation = 3;
            }
            
        }
        else
        {
            rotation = (m_rotationSupplier.getAsDouble() * m_drivetrainSubsystem.SpeedModifier);
        }

        
        SmartDashboard.putNumber("Get Speed", Speed);
        SmartDashboard.putNumber("Get Angle", Angle);


        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        (m_translationXSupplier.getAsDouble() * m_drivetrainSubsystem.SpeedModifier),
                        (m_translationYSupplier.getAsDouble() * m_drivetrainSubsystem.SpeedModifier),
                        rotation,
                        m_drivetrainSubsystem.getGyroscopeRotation()
                )
        );
    }



    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
