package com.tc.app.exchangemonitor;

import javafx.beans.property.BooleanProperty;

public interface Selectable {
  /**
   * The selectableProperty for this object. This property is assumed to return a reference to the
   * implementing object via a call to getBean().
   * @return The selectable property.
   */
  public BooleanProperty selectableProperty();
  public boolean isSelectable();
  public void setSelectable(boolean selectable);
}
