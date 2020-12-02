aws s3 mb s3://boozeonwheels-kops-bucket-state-store --region ap-southeast-1
aws s3api put-bucket-versioning --bucket boozeonwheels-kops-bucket-state-store --versioning-configuration Status=Enabled
export KOPS_CLUSTER_NAME=cluster.boozeonwheels.com
export KOPS_STATE_STORE=s3://boozeonwheels-kops-bucket-state-store


export AWS_ACCESS_KEY=AKIAQ2GFWIPHCJUFVKUH
export AWS_SECRET_KEY=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
kops create cluster --node-count=2 --node-size=t2.medium --master-size=t2.micro --name=cluster.boozeonwheels.com --state=s3://boozeonwheels-kops-bucket-state-store --zones=ap-southeast-1a  --dns-zone=boozeonwheels.com --yes
export KOPS_STATE_STORE=s3://boozeonwheels-kops-bucket-state-store
kops update cluster --name cluster.boozeonwheels.com --yes
kops validate cluster

kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta6/aio/deploy/recommended.yaml
kubectl create serviceaccount dashboard-admin-sa
kubectl create clusterrolebinding dashboard-admin-sa --clusterrole=cluster-admin --serviceaccount=default:dashboard-admin-sa


kubectl get secrets
kubectl describe secret dashboard-admin-sa-token-gb7kh

kubectl get secret $(kubectl get serviceaccount dashboard-admin-sa -o jsonpath="{.secrets[0].name}") -o jsonpath="{.secrets[0].name}" -o jsonpath="{.data.token}" | base64 --decode


https://api.cluster.boozeonwheels.com/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/#!/overview?namespace=default
kubectl describe pods dashboard-metrics-scraper-6c554969c6-vvhcs --namespace=kube-system
kubectl describe pods kubernetes-dashboard-c46b85bbd-xt7tg --namespace=kube-system

kubernetes-dashboard-c46b85bbd-xt7tg

kops delete cluster --name=cluster.boozeonwheels.com --state=s3://boozeonwheels-kops-bucket-state-store --yes
