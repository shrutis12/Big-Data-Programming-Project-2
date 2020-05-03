object PageData {
  def main(args: Array[String]): Unit = {
    val pageData =spark.read.format("com.databricks.spark.csv").option("header", "false").option("inferSchema","true"
).option("delimiter"," ").load("pagecounts-20160101-000000")

	val newColNames = Seq("project_code","page_title","num_hits","page_size")
	val newPageData = pageData.toDF(newColNames: _*)
	
	//q1
	println("number of records in dataset")
	println(newPageData.count)
	
	//q2
	println("record with max page size")
	newPageData.groupBy("project_code","page_title","num_hits").agg(max("page_size").as("max_page_size")).orderBy($"max_page_size".desc).show(1)
	
	//q3
	println("total page views for each project")
	newPageData.select("project_code","num_hits").groupBy("project_code").sum().show()
	
	//q4
	println("count of non english project titles starting with 'The'")
	val thePageTitles = newPageData.filter(col("page_title").startsWith("The_")).filter("project_code != 'en'")
	println(thePageTitles.count)
	
	//q5
	println("most frequent page title and page title term")
	newPageData.groupBy("page_title").agg(count("page_title").as("page_title_counts")).orderBy($"page_title_counts".desc).show(1)
	
	newPageData.withColumn("page_title_term",explode(split($"page_title","_"))).groupBy("page_title_term").count().orderBy($"count".desc).show(1)
	
  }
}