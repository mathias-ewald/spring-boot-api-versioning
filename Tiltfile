SOURCE_IMAGE = os.getenv("SOURCE_IMAGE")
LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = os.getenv("NAMESPACE")

update_settings ( max_parallel_updates = 3 , k8s_upsert_timeout_secs = 120 , suppress_unused_image_warnings = None )

k8s_custom_deploy(
   'spring-boot-api-versioning',
   apply_cmd="tanzu apps workload apply -f workload.yaml --live-update" +
       " --local-path " + LOCAL_PATH +
       " --source-image " + SOURCE_IMAGE +
       " --namespace " + NAMESPACE +
       " --yes > /dev/null" +
       " && kubectl get workload spring-boot-api-versioning --namespace " + NAMESPACE + " -o yaml",
   delete_cmd="tanzu apps workload delete -f workload.yaml --namespace " + NAMESPACE + " --yes" ,
   deps=['pom.xml', './target/classes'],
   container_selector='workload',
   live_update=[
       sync('./target/classes', '/workspace/BOOT-INF/classes')
   ]
)

k8s_resource('spring-boot-api-versioning', port_forwards=["8080:8080"],
   extra_pod_selectors=[{'serving.knative.dev/service': 'spring-boot-api-versioning'}])

allow_k8s_contexts('gke_cso-pcfs-emea-mewald_europe-west3_tap-cluster-demo')
