data "aws_ssm_parameter" "db_username_param" {
  name = var.db_username
}

data "aws_ssm_parameter" "db_password_param" {
  name = var.db_password
}

resource "aws_security_group" "db_sg" {
  name        = "aurora-security-group"
  description = "Allow inbound traffic from VPC"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = var.allow_cidrs 
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "aurora-security-group"
  }
}

resource "aws_db_subnet_group" "that" {
  name       = "main"
  subnet_ids = var.db_subnet_ids
}

resource "aws_rds_cluster" "aurora_cluster" {
  cluster_identifier      = var.db_cluster_identifier
  engine                  = var.db_engine
  engine_version          = var.db_engine_version
  database_name           = var.db_database_name
  master_username         = data.aws_ssm_parameter.db_username_param.value
  master_password         = data.aws_ssm_parameter.db_password_param.value
  skip_final_snapshot     = true
  db_subnet_group_name    = aws_db_subnet_group.that.name
  vpc_security_group_ids  = [aws_security_group.db_sg.id]
}

resource "aws_rds_cluster_instance" "aurora_instance" {
  identifier         = "${var.db_cluster_identifier}-0"
  cluster_identifier = aws_rds_cluster.aurora_cluster.id
  instance_class     = var.db_instance_class
  engine             = var.db_engine
  engine_version     = var.db_engine_version
}
