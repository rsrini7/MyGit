package com.georg
{
	import com.georg.datetime.DateTimeChooser;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.controls.DateField;
	import mx.controls.dataGridClasses.DataGridListData;
	import mx.controls.listClasses.ListData;
	import mx.core.ClassFactory;
	import mx.core.IFactory;
	import mx.events.CalendarLayoutChangeEvent;

	public class DateTimeField extends DateField
	{
		// Using this field, if a dataProvider is used.
		public var dateField:String = "date";
		
		// saves temporarily the time, because the DateField component kills the time of selectedDate
		private var completeDate:Date = new Date(null, 0, 0, 0, 0, 0, 0);
		private var oldDate:Date;
		
		
		public function DateTimeField()
		{
			this.addEventListener(CalendarLayoutChangeEvent.CHANGE, dateChanged);
			this.labelFunction = labelDateTime;
		}
		
		// -------------------------------
		// update data
		// -------------------------------
		
		private function dateChanged(e:CalendarLayoutChangeEvent):void
		{
			// if new date selected, save it to the data-binding variable
			if (data && selectedDate && oldDate.toString() != selectedDate.toString())
			{
				if (listData && listData is DataGridListData)
					data[DataGridListData(listData).dataField] = selectedDate;
				else if (listData is ListData && ListData(listData).labelField in value)
					data[ListData(listData).labelField] = selectedDate;
				else if (value is String)
					data = selectedDate.toString();
				else
					data = value as Date;
			}
			trace("data changed");
		}
		
		override public function set data(value:Object) : void
		{
			var newDate:Date;
			
			if (listData && listData is DataGridListData)
				newDate = value[DataGridListData(listData).dataField];
			else if (listData is ListData && ListData(listData).labelField in value)
				newDate = value[ListData(listData).labelField];
			else if (value is String)
				newDate = new Date(Date.parse(value as String));
			else
				newDate = value as Date;
			
			// update displays
			selectedDate = newDate;
			dropdown.selectedDate = newDate;
			
			// save date
			oldDate = newDate;
			
			super.data = value;
			
			// update display list without cropping time value
			dropdown.dispatchEvent(new CalendarLayoutChangeEvent(CalendarLayoutChangeEvent.CHANGE));
		}
		
		override public function open() : void
		{
			trace("open");
		}
		
		// -------------------------------
		// change Label
		// -------------------------------
		private function numberToTime(value:Number):String
		{
			return (value >= 10) ? String(value) : ("0" + value);
		}
		
		protected function labelDateTime(value:Date):String
		{
			if (value.hours == 0 && value.minutes == 0)
				value = oldDate;
			
			if (value)
				return DateField.dateToString(value, formatString) + " " + numberToTime(value.hours) + ":" + numberToTime(value.minutes);
			else
				return "";
		}
	
		//----------------------------------
		//  dropdownFactory // Overwrite, to include the own DateTimeChooser class
		//----------------------------------
		private var _dropdownFactory:IFactory = new ClassFactory(DateTimeChooser);
		
		[Bindable("dropdownFactoryChanged")]
		override public function get dropdownFactory():IFactory
		{
			return _dropdownFactory;
		}
		
		override public function set dropdownFactory(value:IFactory):void
		{
			_dropdownFactory = value;
			dispatchEvent(new Event("dropdownFactoryChanged"));
		}
		
		// -------------------------------
		// Remove focus handler
		// -------------------------------
		override protected function focusOutHandler(event:FocusEvent):void
		{
			// disable focus lost
		}
		
	}
}