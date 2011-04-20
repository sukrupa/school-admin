require 'rubygems'
require 'roo'
require 'student'
require 'talent'



class WorksheetParser


  LEGENDS_CORNER = "Sl.No"
  NAME_HEADING = 'Name of the Student'
  CASTE_HEADING = 'Caste'
  SUBCASTE_HEADING = 'Subcaste'
  RELIGION_HEADING = 'Religion'
  COMMUNITY_LOCATION_HEADING = 'Community Location'
  STUDENT_ID_HEADING = "Student's ID"
  FAMILY_STATUS_HEADING = "Family Status"
  GENDER_HEADING ='Gender'
  DATE_OF_BIRTH_HEADING = 'DOB'
  SPONSOR_HEADING = 'Sponsor'
  TALENT_HEADING = 'Special Talent in Child'
  ACADEMIC_HEADING = 'Academic Performance of the Child'
  FATHER_HEADING = "Father's Name"
  FATHER_OCCUPATION = "Father's Occupation"
  FATHER_EDUCATION = "Father's Education"
  FATHER_SALARY = "Father's Salary"
  FATHER_MARITAL_STATUS = "Father's Marital Status"

  MOTHER_HEADING = "Mother's Name"
  MOTHER_OCCUPATION = "Mother's Occupation"
  MOTHER_EDUCATION = "Mother's Education"
  MOTHER_SALARY = "Mother's Salary"
  MOTHER_MARITAL_STATUS = "Mother's Marital Status"
  
  GUARDIAN_HEADING = "Guardian's Name"
  GUARDIAN_OCCUPATION = "Guardian's Occupation"
  GUARDIAN_EDUCATION = "Guardian's Education"
  GUARDIAN_SALARY = "Guardian's Salary"
  
  CONTACT_NUMBER_HEADING = "Contact No."
  
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
       name = read_cell_value(row_number,NAME_HEADING)
       date_of_birth = read_cell_value(row_number,DATE_OF_BIRTH_HEADING)
       gender = read_cell_value(row_number,GENDER_HEADING) 
       caste = read_cell_value(row_number,CASTE_HEADING)
       sub_caste = read_cell_value(row_number,SUBCASTE_HEADING)
       family_status =  read_cell_value(row_number,FAMILY_STATUS_HEADING)
       sponsor = read_cell_value(row_number,SPONSOR_HEADING)
       academic_performance = read_cell_value(row_number,ACADEMIC_HEADING)
       
       talents_string = read_cell_value(row_number,TALENT_HEADING)
   
       father_name = read_cell_value(row_number,FATHER_HEADING)
       father_occupation = read_cell_value(row_number, FATHER_OCCUPATION)
       father_education = read_cell_value(row_number,FATHER_EDUCATION)
       father_salary = read_cell_value(row_number,FATHER_SALARY)
       father_marital_status = read_cell_value(row_number,FATHER_MARITAL_STATUS)

       mother_name = read_cell_value(row_number,MOTHER_HEADING)      
             mother_occupation = read_cell_value(row_number, MOTHER_OCCUPATION)   
             mother_education = read_cell_value(row_number,MOTHER_EDUCATION) 
             mother_salary = read_cell_value(row_number,MOTHER_SALARY)
             mother_marital_status = read_cell_value(row_number,MOTHER_MARITAL_STATUS)
       
       #       guardian = read_cell_value(row_number,GUARDIAN_HEADING)      
       #        guardian_occupation = read_cell_value(row_number, GUARDIAN_OCCUPATION)   
       #        guardian_education = read_cell_value(row_number,GUARDIAN_EDUCATION) 
       #        guardian_salary = read_cell_value(row_number,GUARDIAN_SALARY)

           talents = Talent.new(talents_string)


              father_data = {
                :name => father_name,
                :occupation => father_occupation,
                :education => father_education,
                :salary => father_salary,
                :marital_status => father_marital_status
              }
              
              mother_data = {
                :name => mother_name,
                :occupation => mother_occupation,
                :education => mother_education,
                :salary => mother_salary,
                :marital_status => mother_marital_status
              }
              
              if mother_data
                mother = Caregiver.new(mother_data)
              end
              if father_data
                father = Caregiver.new(father_data) 
       
              end

       #  guardian = Cargiver.new()
       
       
       if (!name.nil? or  !student_id.nil?)
         student_data = {
           :religion => religion,
           :caste => caste,
           :sub_caste => sub_caste,
           :family_status => family_status,
           :sponsor => sponsor,
           :community_location => community_location,
           :student_id => student_id,
           :name => name,
           :date_of_birth => date_of_birth,
           :gender => gender,
           :student_class => @student_class,
           :student_performance => academic_performance
         }
         student = Student.new(student_data)
         @students_and_talents_array << [student, talents,[father,mother]]
       end
       
	   end
	   @students_and_talents_array
	   
	   
	end
	

	def read_cell_value(row_number,column_heading)
	  column_number = @column_headings[column_heading]
	  cell_value = @worksheet.cell(row_number,column_number)
	  
	  if (cell_value == '-' || cell_value == 0 || cell_value == "'-")
	    return nil
	  end
	  if column_heading == DATE_OF_BIRTH_HEADING
	    return cell_value.to_s
    else
      
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
	
  
end

