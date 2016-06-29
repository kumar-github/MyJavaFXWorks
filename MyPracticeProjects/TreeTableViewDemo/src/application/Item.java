package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item
{

  private final IntegerProperty id   = new SimpleIntegerProperty();
  private final StringProperty  name = new SimpleStringProperty();

  public Item( final int id, final String name )
  {
    this.id.set( id );
    this.name.set( name );
  }

  public int getId()
  {
    return id.get();
  }

  public String getName()
  {
    return name.get();
  }

  public void setId( final int id )
  {
    this.id.set( id );
  }

  public void setName( final String name )
  {
    this.name.set( name );
  }

  public IntegerProperty idProperty()
  {
    return id;
  }

  public StringProperty nameProperty()
  {
    return name;
  }
}

