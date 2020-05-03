#Big Data Programming Project 2 Read me file

#SPARK
1.Create spark master and worker on your VM using following commands:

	sudo docker run --name spark-master -h spark-master -e ENABLE_INIT_DAEMON=false -d bde2020/spark-master:2.4.4-hadoop2.7

	sudo docker run --name spark-worker-1 --link spark-master:spark-master -e ENABLE_INIT_DAEMON=false -d bde2020/spark-worker:2.4.4-hadoop2.7

2. If worker and master already exist then just start them using below commands:

	sudo docker start spark-master
	sudo docker start spark-worker-1
	
3. Start the spark-master bash using below command:

	sudo docker exec -it spark-master
	
4. Copy the PageData.scala present in the 'spark' folder into directory containing the dataset. (change permissions if required)

5. From the directory start the spark-shell. (/spark/bin/spark-shell) (this might change depending on your directory structure)

6. In the scala command line execute following commands:
	
	:load PageData.scala
	PageData.main(Array())


#Graph Processing

Repeat steps 1-3 as mentioned above. Repeat only 2-3 if master and worker already exist.

4. Copy PatentGraph.scala present in the 'graph' folder into directory containing the dataset.(change permissions if required)

5. Go to directory /spark/conf. Create a file spark-defaults.conf and add the following line to it:

	spark.jars.packages graphframes:graphframes:0.2.0-spark2.0-s_2.11
	

6. From the directory containing scala code and datset start the spark-shell. (/spark/bin/spark-shell) (this might change depending on 	your directory structure)

7. In the scala command line execute following commands:
	
	:load PatentGraph.scala
