require 'rubygems'
require 'roo'
require 'student'
require 'talent'



class WorksheetParser


  LEGENDS_CORNER = "Sl.No"
  NAME_HEADING = 'Name of the Student'
  RELIGION_HEADING = 'Religion'
  COMMUNITY_LOCATION_HEADING = 'Community Location'
  STUDENT_ID_HEADING = "Student's ID"
  FATHER_HEADING = "Father's Name"
  MOTHER_HEADING = "Mother's Name"
  GENDER_HEADING ='Gender'
  DATE_OF_BIRTH_HEADING = 'DOB'
  TALENT_HEADING = 'Special Talent in Child'
  
  def initialize(worksheet)
    @worksheet = worksheet
    @student_class = worksheet.default_sheet.strip()
    @this_sheets_column_heading_row = self.calculate_column_heading_row
    @this_sheets_starting_corner = @this_sheets_column_heading_row + 1
    @students_and_talents_array = []
    @column_headings = self.create_column_heading_hash_map
  end
  
	
	def starting_corner
	 @this_sheets_starting_corner
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
	     
	     religion = read_cell_value(row_number,RELIGION_HEADING)
       community_location = read_cell_value(row_number,COMMUNITY_LOCATION_HEADING)
       student_id = read_cell_value(row_number,STUDENT_ID_HEADING)
       father = read_cell_value(row_number,FATHER_HEADING)
       mother = read_cell_value(row_number,MOTHER_HEADING)
       name = read_cell_value(row_number,NAME_HEADING)
       date_of_birth = read_cell_value(row_number,DATE_OF_BIRTH_HEADING)
       gender = read_cell_value(row_number,GENDER_HEADING)
       
       talents_string = read_cell_value(row_number,TALENT_HEADING)
       talents = Talent.new(talents_string)
       
       
       
       if (!name.nil? or  !student_id.nil?)
         student_data = {
           :religion => religion,
           :community_location => community_location,
           :student_id => student_id,
           :father => father,
           :mother => mother,
           :name => name,
           :date_of_birth => date_of_birth,
           :gender => gender,
           :student_class => @student_class
         }
         student = Student.new(student_data)
         @students_and_talents_array << [student, talents]
       end
       
	   end
	   @students_and_talents_array
	end
	

	def read_cell_value(row_number,column_heading)
	  column_number = @column_headings[column_heading]
	  cell_value = @worksheet.cell(row_number,column_number)
	  
	  if (cell_value == '-')
	    return nil
	  end
	  
    if (@worksheet.celltype(row_number,column_number) == :float)
 	      return Integer(cell_value)
    elsif (@worksheet.celltype(row_number,column_number) == :string)
        return cell_value.strip()
    elsif (@worksheet.celltype(row_number,column_number) == :date)
        return cell_value.to_s
    end
    return cell_value
	end
	
  
end

