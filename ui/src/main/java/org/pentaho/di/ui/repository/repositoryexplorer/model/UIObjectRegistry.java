/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package org.pentaho.di.ui.repository.repositoryexplorer.model;

import java.lang.reflect.Constructor;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.repository.IUser;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.RepositoryElementMetaInterface;

public class UIObjectRegistry {

  public static final Class<?> DEFAULT_UIREPOSITORYUSER_CLASS = UIRepositoryUser.class;
  public static final Class<?> DEFAULT_UIJOB_CLASS = UIJob.class;
  public static final Class<?> DEFAULT_UITRANS_CLASS = UITransformation.class;
  public static final Class<?> DEFAULT_UIDIR_CLASS = UIRepositoryDirectory.class;
  public static final Class<?> DEFAULT_DBCONN_CLASS = UIDatabaseConnection.class;
  private static UIObjectRegistry instance;

  private Class<?> repositoryUserClass = DEFAULT_UIREPOSITORYUSER_CLASS;
  private Class<?> jobClass = DEFAULT_UIJOB_CLASS;
  private Class<?> transClass = DEFAULT_UITRANS_CLASS;
  private Class<?> dirClass = DEFAULT_UIDIR_CLASS;
  private Class<?> dbConnClass = DEFAULT_DBCONN_CLASS;

  private UIObjectRegistry() {

  }

  public static UIObjectRegistry getInstance() {
    if ( instance == null ) {
      instance = new UIObjectRegistry();
    }
    return instance;
  }

  public void registerUIRepositoryUserClass( Class<?> repositoryUserClass ) {
    this.repositoryUserClass = repositoryUserClass;
  }

  public Class<?> getRegisteredUIRepositoryUserClass() {
    return this.repositoryUserClass;
  }

  public void registerUIJobClass( Class<?> jobClass ) {
    this.jobClass = jobClass;
  }

  public Class<?> getRegisteredUIJobClass() {
    return this.jobClass;
  }

  public void registerUITransformationClass( Class<?> transClass ) {
    this.transClass = transClass;
  }

  public Class<?> getRegisteredUITransformationClass() {
    return this.transClass;
  }

  public void registerUIRepositoryDirectoryClass( Class<?> dirClass ) {
    this.dirClass = dirClass;
  }

  public Class<?> getRegisteredUIRepositoryDirectoryClass() {
    return this.dirClass;
  }

  public void registerUIDatabaseConnectionClass( Class<?> dbConnClass ) {
    this.dbConnClass = dbConnClass;
  }

  public Class<?> getRegisteredUIDatabaseConnectionClass() {
    return this.dbConnClass;
  }

  public IUIUser constructUIRepositoryUser( IUser user ) throws UIObjectCreationException {
    try {
      Constructor<?> constructor = repositoryUserClass.getConstructor( IUser.class );
      if ( constructor != null ) {
        return (IUIUser) constructor.newInstance( user );
      } else {
        throw new UIObjectCreationException( "Unable to get the constructor for " + repositoryUserClass );
      }
    } catch ( Exception e ) {
      throw new UIObjectCreationException( "Unable to instantiate object for " + repositoryUserClass );
    }
  }

  public UIJob constructUIJob( RepositoryElementMetaInterface rc, UIRepositoryDirectory parent, Repository rep ) throws UIObjectCreationException {
    try {
      Constructor<?> constructor =
        jobClass.getConstructor(
          RepositoryElementMetaInterface.class, UIRepositoryDirectory.class, Repository.class );
      if ( constructor != null ) {
        return (UIJob) constructor.newInstance( rc, parent, rep );
      } else {
        throw new UIObjectCreationException( "Unable to get the constructor for " + jobClass );
      }
    } catch ( Exception e ) {
      throw new UIObjectCreationException( "Unable to instantiate object for " + jobClass );
    }
  }

  public UITransformation constructUITransformation( RepositoryElementMetaInterface rc,
    UIRepositoryDirectory parent, Repository rep ) throws UIObjectCreationException {
    try {
      Constructor<?> constructor =
        transClass.getConstructor(
          RepositoryElementMetaInterface.class, UIRepositoryDirectory.class, Repository.class );
      if ( constructor != null ) {
        return (UITransformation) constructor.newInstance( rc, parent, rep );
      } else {
        throw new UIObjectCreationException( "Unable to get the constructor for " + transClass );
      }
    } catch ( Exception e ) {
      throw new UIObjectCreationException( "Unable to instantiate object for " + transClass );
    }
  }

  public UIRepositoryDirectory constructUIRepositoryDirectory( RepositoryDirectoryInterface rd,
    UIRepositoryDirectory uiParent, Repository rep ) throws UIObjectCreationException {
    try {
      Constructor<?> constructor =
        dirClass.getConstructor(
          RepositoryDirectoryInterface.class, UIRepositoryDirectory.class, Repository.class );
      if ( constructor != null ) {
        return (UIRepositoryDirectory) constructor.newInstance( rd, uiParent, rep );
      } else {
        throw new UIObjectCreationException( "Unable to get the constructor for " + dirClass );
      }
    } catch ( Exception e ) {
      throw new UIObjectCreationException( "Unable to instantiate object for " + dirClass );
    }
  }

  public UIDatabaseConnection constructUIDatabaseConnection( DatabaseMeta dbmeta, Repository rep ) throws UIObjectCreationException {
    try {
      Constructor<?> constructor = dbConnClass.getConstructor( DatabaseMeta.class, Repository.class );
      if ( constructor != null ) {
        return (UIDatabaseConnection) constructor.newInstance( dbmeta, rep );
      } else {
        throw new UIObjectCreationException( "Unable to get the constructor for " + dbConnClass );
      }
    } catch ( Exception e ) {
      throw new UIObjectCreationException( "Unable to instantiate object for " + dbConnClass );
    }
  }
}
