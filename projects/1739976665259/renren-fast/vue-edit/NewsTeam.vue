<template>
    <el-dialog
            :title="!dataForm.newsTeamId ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
                    <el-form-item label="n_id" prop="nId">
                        <el-input v-model="dataForm.nId" placeholder="n_id"></el-input>
                    </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    export default {
        data () {
            return {
                visible: false,
                dataForm: {
                    nId: ''
        },
            dataRule: {
                nId: [{ required: true, message: 'n_id不能为空', trigger: 'blur' }]
            }
        }
        },
        methods: {
            init (id) {
                this.dataForm.newsTeamId = id || 0
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    // <!-- 请把 newsTeamId 替换成正确的ID -->
                    if (this.dataForm.newsTeamId) {
                        this.$http({
                            url: this.$http.adornUrl(`/generator/newsTeam/info/${this.dataForm.newsTeamId}`),
                            method: 'get',
                            params: this.$http.adornParams()
                    }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.nId = data.newsTeam.nId
                            }
                        })
                    }
                })
            },
            // 表单提交
            dataFormSubmit () {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                    this.$http({
                        url: this.$http.adornUrl(`/generator/newsTeam/${this.dataForm.newsTeamId? 'save' : 'update'}`),
                        method: 'post',
                        data: this.$http.adornData({
                            'nId': 'nId' || undefined
                        })
                }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.$message({
                                message: '操作成功',
                                type: 'success',
                                duration: 1500,
                                onClose: () => {
                                    this.visible = false
                                    this.$emit('refreshDataList')
                                }
                        })
                        } else {
                            this.$message.error(data.msg)
                        }
                    })
                }
                })
            }
        }
    }
</script>
