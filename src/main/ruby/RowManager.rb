require 'rubygems'
require 'roo'



class Worksheet


  LEGENDS_CORNER = "Sl.No"
  
  def initialize(worksheet)
    @worksheet = worksheet
  end
  
	
	def starting_corner
	  1.upto(@worksheet.last_row) do |row_number|
		  if @worksheet.cell(row_number,'A') == LEGENDS_CORNER
			  @this_sheets_starting_corner = row_number+1
			  break
		  end
	  end
	  @this_sheets_starting_corner
  end
	


	def skipped_columns
	  
	  @columns_to_skip=[]
	  
	  1.upto(@worksheet.last_column) do |column_number|
		  skip_this_column = true
		
		  @this_sheets_starting_corner.upto(@worksheet.last_row) do |row_number|
			  if !@worksheet.empty?(row_number,column_number)
				  skip_this_column = false
				  break
			  end
		  end
		
		  if skip_this_column
			  @columns_to_skip += [column_number]
		  end		
		  
		end
		
		@columns_to_skip
		
	end
	
  students = Excelx.new(ARGV[0])
  students.default_sheet = students.sheets.first
  row_manager = Worksheet.new(students)
  puts row_manager.starting_corner
  puts row_manager.skipped_columns
  
end

