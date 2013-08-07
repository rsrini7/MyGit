package custom.datechooser
{
    import flash.events.Event;
    
    import mx.collections.ArrayCollection;
    import mx.controls.ComboBox;
    import mx.controls.DateChooser;
    import mx.core.UITextField;
    import mx.core.mx_internal;
    import mx.events.DateChooserEvent;
    import mx.events.DateChooserEventDetail;
    
    use namespace mx_internal;
    
    public class MyDateChooser extends DateChooser
    {
    	
    	private var comboBox:ComboBox;
    	
        // ---------------------------------------------------------------------------------------
        //
        //  Constructor
        //
        // ---------------------------------------------------------------------------------------
        
        /**
         * Class constructor. 
         * 
         */        
        public function MyDateChooser()
        {
            super();
        }
        
        // ---------------------------------------------------------------------------------------
        //
        //  Overridden methods
        //
        // ---------------------------------------------------------------------------------------
        
        /**
         * @inheritDoc
         * 
         */        
        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
        {
        	
        	unscaledWidth = unscaledWidth + 50;
        	//unscaledHeight = unscaledHeight + 20;
        	
            super.updateDisplayList(unscaledWidth, unscaledHeight);
            
            // Center the month field.
            monthDisplay.x = (unscaledWidth - monthDisplay.width) * 0.1;
            
            var borderThickness:Number = getStyle("borderThickness");
			var cornerRadius:Number = getStyle("cornerRadius");
			var borderColor:Number = getStyle("borderColor");
			
			var w:Number = unscaledWidth - borderThickness*2;
			var h:Number = unscaledHeight - borderThickness*2;
            
            comboBox.setActualSize(67, 20);
			comboBox.move(w-100, h - 173);
            
//            yearDisplay.x = (unscaledWidth - yearDisplay.width) * 2.7;
        }
        
        /**
		 *  @private
		 *  Erstellt noch den Time NumericStepper
		 */
		override protected function createChildren():void
		{
			super.createChildren();
			
			if (!comboBox){
				var arrayCollection:ArrayCollection = new ArrayCollection();
				arrayCollection.addItem(new String("2000"));
				arrayCollection.addItem(new String("2001"));
				arrayCollection.addItem(new String("2002"));
				arrayCollection.addItem(new String("2003"));
				arrayCollection.addItem(new String("2004"));
				arrayCollection.addItem(new String("2005"));
				arrayCollection.addItem(new String("2006"));
				arrayCollection.addItem(new String("2007"));
				arrayCollection.addItem(new String("2008"));
				arrayCollection.addItem(new String("2009"));
				arrayCollection.addItem(new String("2010"));
				
				
				comboBox = new ComboBox();
				comboBox.dataProvider = arrayCollection;
				this.addEventListener(DateChooserEvent.SCROLL, onChangeYear);
				comboBox.addEventListener(Event.CHANGE, onChangeDropDownYears);
				comboBox.owner = this;
//				this.months.addEventListener(Event.CHANGE, onChangeDropDownMonth);
				addChild(comboBox);
			}
		}
                
        /**
			 * onChangeMonth
			 * 
			 * @access private
			 * @param DateChooserEvent
			 * @return void
			 */
			private function onChangeYear( event : DateChooserEvent ) : void 
			{
//				if (event.detail == DateChooserEventDetail.NEXT_MONTH)
//					this.months.selectedIndex++;
//					
//				if (event.detail == DateChooserEventDetail.PREVIOUS_MONTH) 
//					this.months.selectedIndex--;
				
				if (event.detail == DateChooserEventDetail.PREVIOUS_YEAR) {
//					this.months.selectedIndex = 11;
					comboBox.selectedIndex--;
				}
				
				if (event.detail == DateChooserEventDetail.NEXT_YEAR) {
//					this.months.selectedIndex = 0;
					comboBox.selectedIndex++;
				}
				
			}
			
//			/**
//			 * onChangeDropDownMonth
//			 * 
//			 * @access private
//			 * @param Event event
//			 * @return void
//			 */
//			private function onChangeDropDownMonth( event : Event ) : void 
//			{
//				this.calend.displayedMonth = (this.months.selectedIndex as Number);
//			}
			
			/**
			 * onChangeDropDownYears
			 * 
			 * @access private
			 * @param Event event
			 * @return void
			 */
			private function onChangeDropDownYears( event : Event ) : void 
			{
				this.displayedYear = this.comboBox.selectedItem.toString();
			}
                
        /**
         * @inheritDoc
         * 
         */                
        override mx_internal function createYearDisplay(childIndex:int):void
        {
            if (!yearDisplay)
            {
                // Only create the yearField without adding it. We create the empty
                // field to avoid possible errors. This is not an ideal solution but
                // I'm too lazy and tired to override all the methods that are
                // affected by a way or another by this component.
                yearDisplay = new UITextField();
            }
        }
    }
}