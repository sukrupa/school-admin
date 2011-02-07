
require 'rubygems'

class SQLGenerator
  
  def generate_sql(students)
      sql_statements = []
      students.each do |student|
        sql_statements << "INSERT INTO student (#{student.attribute_names.join(',')}) VALUES (#{values_for student});"
      end
      sql_statements
  end

  def values_for(student)
    student.attribute_values.map { |value| "'#{value}'" }.join(',')
  end
  
end
