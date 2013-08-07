package custom.datechooser
{
    import flash.text.TextFormat;
    
    import mx.controls.DateChooser;
    import mx.core.IFontContextComponent;
    import mx.core.UITextField;
    import mx.core.mx_internal;
    import mx.managers.IFocusManagerComponent;
    import flash.display.DisplayObject;
    import mx.core.IUITextField;
    
    use namespace mx_internal;
    
    public class CustomDateChooser extends DateChooser
    {
        // ---------------------------------------------------------------------------------------
        //
        //  Constructor
        //
        // ---------------------------------------------------------------------------------------
        
        /**
         * Class constructor. 
         * 
         */        
        public function CustomDateChooser()
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
            super.updateDisplayList(unscaledWidth, unscaledHeight);
            
            // Center the month field.
            monthDisplay.x = (unscaledWidth - monthDisplay.width) * 0.3;
            
            //yearDisplay.x = (unscaledWidth - yearDisplay.width) * 0.7;
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
//                yearDisplay = new UITextField();

			yearDisplay = IUITextField(createInFontContext(UITextField));
            
            var textFormat:TextFormat = determineTextFormatFromStyles();
            yearDisplay.defaultTextFormat = textFormat;
            yearDisplay.visible = false;
            yearDisplay.selectable = false;
            
            if (childIndex == -1)
                addChild(DisplayObject(yearDisplay));
            else
                addChildAt(DisplayObject(yearDisplay), childIndex);
            
//            var dateHeaderStyleName:Object = getStyle("headerStyleName");
//            if (!dateHeaderStyleName)
//                dateHeaderStyleName = this;
//            yearDisplay.styleName = dateHeaderStyleName;
                
                
            }
        }
    }
}