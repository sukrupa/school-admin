
require 'rubygems'

class SQLGenerator
  
  def generate_sql(student)
      "INSERT INTO student (#{student.attribute_names.join(',')}) VALUES (#{values_for student});"
  end

  def values_for(student)
    student.attribute_values.map { |value| "'#{value}'" }.join(',')
  end
  
end
