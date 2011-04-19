
require 'rubygems'

class SQLGenerator
  
  def generate_sql(students_and_talents_array)
      sql_statements = []
      students_and_talents_array.map { |x| x[1] }.map(&:talents).flatten.uniq.sort.each do |talent|  
        sql_statements <<"INSERT INTO talent (description) VALUES ('#{talent}');"
      end
      students_and_talents_array.each do |student_and_talents|
        student = student_and_talents[0]
        talents = student_and_talents[1]
        caregiver = student_and_talents[2]

        if !caregiver.attribute_names.empty?
                  
          sql_statements <<   "MERGE INTO CAREGIVER USING (VALUES(#{values_for caregiver})) ON (name = '#{caregiver.name}' AND occupation = '#{caregiver.occupation}')
                              WHEN NOT MATCHED THEN INSERT(#{caregiver.attribute_names.join(',')}) VALUES (#{values_for caregiver});"
          sql_statements << "INSERT INTO student (#{student.attribute_names.join(',')}, father_id) VALUES (#{values_for student}, (SELECT id FROM CAREGIVER WHERE name = '#{caregiver.name}' AND occupation = '#{caregiver.occupation}'));"       
        elsif  
          sql_statements << "INSERT INTO student (#{student.attribute_names.join(',')}) VALUES (#{values_for student});"
      end
        talents.talents.each do |talent|
          sql_statements << "INSERT INTO student_talent (student_id,talent_id)
          SELECT student.id,talent.id FROM STUDENT,TALENT WHERE student.student_id = '#{student.student_id}' AND (talent.description = '#{talent}');"
        end

      end
      sql_statements
  end

  def values_for(student)
    student.attribute_values.map { |value| "'#{value}'" }.join(',')
  end
  
end
