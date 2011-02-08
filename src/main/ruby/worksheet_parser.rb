require 'rubygems'
require 'roo'
require 'student'



class WorksheetParser


  LEGENDS_CORNER = "Sl.No"
  NAME_HEADING = 'Name'
  GENDER_HEADING ='Gender'
  DATE_OF_BIRTH_HEADING = 'DOB'
  
  def initialize(worksheet)
    @worksheet = worksheet
    @this_sheets_column_heading_row = self.calculate_column_heading_row
    @this_sheets_starting_corner = @this_sheets_column_heading_row + 1
    @students_array = []
    @column_headings = self.create_column_heading_hash_map
  end
  
	
	def starting_corner
	 @this_sheets_starting_corner
	end
	
	def skipped_columns
	 @columns_to_skip
  end
	
	
	def calculate_column_heading_row
	  1.upto(@worksheet.last_row) do |row_number|
		  if @worksheet.cell(row_number,'A') == LEGENDS_CORNER
			  @this_sheets_starting_corner = row_number
			  break
		  end
	  end
	  @this_sheets_starting_corner
  end


  def create_column_heading_hash_map
    @column_headings = {}
    1.upto(@worksheet.last_column) do |column_number|
      cell_value = @worksheet.cell(@this_sheets_column_heading_row,column_number)
      if !cell_value.nil?
        @column_headings[cell_value.strip()] = column_number
      end
    end
    @column_headings
  end
	
	
	def parse
	   @this_sheets_starting_corner.upto(@worksheet.last_row) do |row_number|
	     
       name = read_cell_value(row_number,NAME_HEADING)
       date_of_birth = read_cell_value(row_number,DATE_OF_BIRTH_HEADING)
       gender = read_cell_value(row_number,GENDER_HEADING)
       
       if (!name.nil? or  !date_of_birth.nil? or !gender.nil?)
         student = Student.new :name => name, :date_of_birth => date_of_birth, :gender => gender
         @students_array += [student]
       end
       
	   end
	   @students_array
	end
	

	def read_cell_value(row_number,column_heading)
	  column_number = @column_headings[column_heading]
	  cell_value = @worksheet.cell(row_number,column_number)
    if (@worksheet.celltype(row_number,column_number) == :float)
 	      return Integer(cell_value)
    elsif (@worksheet.celltype(row_number,column_number) == :string)
        return cell_value.strip()
    end
    return cell_value
	end
	
  
end

