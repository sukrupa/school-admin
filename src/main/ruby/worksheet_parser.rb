require 'rubygems'
require 'roo'



class WorksheetParser


  LEGENDS_CORNER = "Sl.No"
  
  def initialize(worksheet)
    @worksheet = worksheet
    @this_sheets_starting_corner = self.calculate_starting_corner
    @columns_to_skip = self.calculate_skipped_columns
    @students_array = []
  end
  
	
	def starting_corner
	 @this_sheets_starting_corner
	end
	
	def skipped_columns
	 @columns_to_skip
    end
	
	def calculate_starting_corner
	  1.upto(@worksheet.last_row) do |row_number|
		  if @worksheet.cell(row_number,'A') == LEGENDS_CORNER
			  @this_sheets_starting_corner = row_number+1
			  break
		  end
	  end
	  @this_sheets_starting_corner
  end


	def calculate_skipped_columns
	  
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
	
	def parse
	   
	   @this_sheets_starting_corner.upto(@worksheet.last_row) do |row_number|
	     rowposition = 0
	     student = []
	   
	     1.upto(@worksheet.last_column) do |column_number|
	       if !@columns_to_skip.include?(column_number)
	         
	         cell_value = @worksheet.cell(row_number,column_number)
	         
	         if (@worksheet.celltype(row_number,column_number) == :float)
      	        cell_value = Integer(cell_value)
	         elsif (@worksheet.celltype(row_number,column_number) == :string)
	              cell_value = cell_value.strip()
	         end
	         
	         student[rowposition] = cell_value
	         rowposition = rowposition + 1
	       
	       end
	     
	     end
	     
	     @students_array += [Student.from_excel(student)]
	   end
	   @students_array
	end
  
end

